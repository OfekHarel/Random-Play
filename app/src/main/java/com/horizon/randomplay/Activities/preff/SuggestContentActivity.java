package com.horizon.randomplay.Activities.preff;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.SwitchCompat;

import com.google.android.material.textfield.TextInputLayout;
import com.horizon.randomplay.Activities.MainActivity;
import com.horizon.randomplay.Activities.base.BaseActivity;
import com.horizon.randomplay.R;
import com.horizon.randomplay.util.JavaMailAPI;

import java.util.Objects;

public class SuggestContentActivity extends BaseActivity {

    private enum ContentType {
        MOVIE("Movie"),
        SERIES("Series");

        private final String type;
        ContentType(String type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return type;
        }

        public ContentType getOther() {
            return this == MOVIE ? SERIES : MOVIE;
        }
    }

    private ContentType content = ContentType.SERIES;
    private TextView contentType;
    private TextInputLayout name;
    private TextInputLayout moods;
    private TextInputLayout streaming;
    private TextInputLayout email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggest_content);

        SwitchCompat contentSwitch = findViewById(R.id.content_switch);
        this.contentType = findViewById(R.id.content_title);
        this.contentType.setText(content.toString());
        contentSwitch.setOnClickListener(view -> {
            preformVibration(5);
            content = content.getOther();
            contentType.setText(content.toString());
        });

        this.name = findViewById(R.id.content_name_input);
        this.moods = findViewById(R.id.content_mood_input);
        this.streaming = findViewById(R.id.content_streaming_input);
        this.email = findViewById(R.id.content_person_email);

        findViewById(R.id.suggest_finish_btn).setOnClickListener(view -> {
            if (!checkErrors()) {
                sendContentFormEmail();
                redirectActivity(this, MainActivity.class);
            } else {
                preformVibration(10);
            }
        });
    }

    private boolean checkErrors() {
        return !checkName() | !checkEmail();
    }

    private boolean checkName() {
        String raw = Objects.requireNonNull(name.getEditText()).getText().toString().trim();
        boolean val = true;
        name.setError(null);
        if (raw.isEmpty()) {
            name.setError(getResources().getString(R.string.empty_error));
            val = false;
        }
        return val;
    }

    private boolean checkEmail() {
        String raw = Objects.requireNonNull(email.getEditText()).getText().toString().trim();
        boolean val = true;
        email.setError(null);
        if (raw.isEmpty()) {
            email.setError(getResources().getString(R.string.empty_error));
            val = false;
        }
        else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(raw).matches()) {
            email.setError("Email not valid");
            val = false;
        }
        return val;
    }

    private void sendContentFormEmail() {
        final String name_str = Objects.requireNonNull(name.getEditText()).getText().toString().trim();
        final String mood_str = Objects.requireNonNull(moods.getEditText()).getText().toString().trim();
        final String streaming_str = Objects.requireNonNull(streaming.getEditText()).getText().toString().trim();
        final String emailFrom = Objects.requireNonNull(email.getEditText()).getText().toString().trim();

        final String subject = String.format("RandomPlayContactSuggestion: %s",
                emailFrom.substring(0, emailFrom.indexOf("@")));
        final String body = String.format(
                "Type: %s\nName: %s\nVibes: %S\nStream In: %s\nFrom: %s",
                content.toString(), name_str, mood_str, streaming_str, emailFrom);
        new JavaMailAPI(subject, body).execute();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        redirectActivity(this, MainActivity.class);
    }
}

import EmailHelper

class Params:
    username = "random.play.app@gmail.com"
    password = "qmlzowneumdkkbqw"


class Analasis:
    def __init__(self, emails: list):
        self.emails = emails


    def top_to_bottom_new(self) -> list:
        pass


if __name__ == '__main__':
    helper = EmailHelper.Helper((Params.username, Params.password))

    emails = helper.extract_emails()
    print(emails)
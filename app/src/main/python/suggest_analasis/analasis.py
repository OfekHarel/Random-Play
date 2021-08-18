import EmailHelper


class Params:
    username = "random.play.app@gmail.com"
    password = "qmlzowneumdkkbqw"


class Analasis:
    def __init__(self, emails: list):
        self.emails = emails


    def get_names(self) -> list:
        pass


    def get_vibes(self) -> list:
        pass


    def get_stream(self) -> list:
        pass


    def top_to_bottom_new(self) -> list:
        pass


    def display(self):
        pass


if __name__ == '__main__':
    helper   = EmailHelper.Helper((Params.username, Params.password))
    analasis = Analasis(helper.extract_emails())
    analasis.display()

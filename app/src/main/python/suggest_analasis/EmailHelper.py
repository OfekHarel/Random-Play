import imaplib
import email
from email.header import decode_header
import webbrowser
import os

class Helper:

    def __init__(self, carditinals: tuple):
        self._email = carditinals[0]
        self._password = carditinals[1]
        self.imap = None

        self.login()


    def login(self):
        self.imap = imaplib.IMAP4_SSL("imap.gmail.com")
        self.imap.login(self._email, self._password)
        self.imap.select()

    
    def close(self):
        self.imap.close()
        self.imap.logout()


    def email_body(self, raw) -> dict:
        index = 0
        for s in raw:
            if s.find('Content-Transfer-Encoding') != -1:
                body_raw = raw[index + 3:]
                try:
                    body = {
                        "Name": body_raw[0].replace('\r', '').replace('Name: ', ''),
                        "Vibes": body_raw[1].replace('\r', '').replace('Vibes: ', ''),
                        "Stream": body_raw[2].replace('\r', '').replace('Stream In: ', ''),
                        "From": body_raw[3].replace('\r', '').replace('From: ', '')}

                    return body

                except:
                    pass

            index += 1


    def extract_emails(self) -> list:
        emails = []

        typ, message_numbers = self.imap.search(None, 'ALL') 
        for num in message_numbers[0].split():
            typ, data = self.imap.fetch(num, '(RFC822)')
            data1 = str(data[0][1].decode('utf-8')).split('\n')
            emails.append(self.email_body(data1))

        return emails

import requests
from xml.etree import ElementTree
import xmltodict
import os

series_name = input("Wanted Series: ")
OUT_DIR = 's_{}.txt'.format(series_name.replace(" ", "_"))
DATABASE_DIR = r'A:\Software Dev\Projects\RandomPlay\netflix_helper\out\NetflixSeriesData.txt'

def get_series_id():
    with open(DATABASE_DIR, 'r') as database:
        for line in database.readlines():
            index = line.lower().find(series_name.lower())
            if index != -1:
                return int(line[len(series_name) + 7:].replace("\n", ""))
        return -1


def export_data_file(series_id):
    with open(os.path.join(os.getcwd(), "netflix_helper", OUT_DIR), 'a') as out:

        s_url = 'http://api.netflix.com/catalog/titles/series/{}/seasons'.format(series_id)
        response = requests.get(s_url)
        dict_data = xmltodict.parse(response.content)
        s_base_data = list(dict_data['catalog_titles'].values())[0]
        
        for s in s_base_data:
            s_index = s['title']['@regular']
            out.write("\n" + str(s_index) + "\n")

            e_url = ""
            for url in s['link']:
                if url['@title'] == 'episodes':
                    e_url = url['@href']
            
            response = requests.get(e_url)
            dict_data = xmltodict.parse(response.content)
            e_base_data = list(dict_data['catalog_titles'].values())[0]

            print(s_index, end='\r')

            for e in e_base_data:
                episode_name = e['title']['@regular']

                episode_id = str(e['id']).split("/")
                episode_id = int(episode_id[len(episode_id) - 1])

                out.write(episode_name + " ?" + str(episode_id) + "\n")


if __name__ == "__main__":
    series_id = get_series_id()
    if series_id == -1:
        print("INVALID series -> Search for typos!")
    else:
        print("Exporting data...")
        export_data_file(series_id)
        print("\nDone!")

import requests
from xml.etree import ElementTree
import xmltodict
import os

BASE_DIR = r'A:\Software Dev\Projects\RandomPlay\Random-Play\app\src\main\python\netflix_helper'
DATABASE_DIR = os.path.join(BASE_DIR,  'out', 'NetflixSeriesData.txt')

def get_series_id():
    with open(DATABASE_DIR, 'r') as database:
        for line in database.readlines():
            index = line.lower().find(series_name.lower())
            if index != -1:
                return int(line[line.find("|") + 5:].replace("\n", ""))
        return -1


def export_data_file(series_id):
    with open(OUT_DIR, 'x') as out:

        s_url = 'http://api.netflix.com/catalog/titles/series/{}/seasons'.format(series_id)
        response = requests.get(s_url)
        dict_data = xmltodict.parse(response.content)
        s_base_data = list(dict_data['catalog_titles'].values())[0]
        
        is_first = True
        for s in s_base_data:
            s_index = s['title']['@regular']
            season_str = ""
            if is_first:
                season_str = str(s_index) + "\n"
                is_first = False
            else:
                season_str = "\n" + str(s_index) + "\n"
            out.write(season_str)

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
    done = False
    while not done:
        series_name = input("Wanted Series: ")
        
        if series_name == 'q':
            done = True
            continue

        OUT_DIR = os.path.join(BASE_DIR, 's_{}.txt'.format(series_name.replace(" ", "_").lower()))
        series_id = get_series_id()
        if series_id == -1:
            print("INVALID series -> Search for typos!")
        else:
            export_data_file(series_id)
            print("\nDone!\n")

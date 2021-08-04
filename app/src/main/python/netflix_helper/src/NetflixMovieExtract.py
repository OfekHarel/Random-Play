import requests
from xml.etree import ElementTree
import xmltodict
import os

IN_OUT_DIT = r'A:\Software Dev\Projects\RandomPlay\netflix_helper\m_marvel.txt'
DATABASE_DIR = r'A:\Software Dev\Projects\RandomPlay\netflix_helper\out\NetflixMovieData.txt'

with open(IN_OUT_DIT, 'r+') as in_movies:
    with open(DATABASE_DIR, 'r') as database:
        
        movies = in_movies.readlines()
        
        database_lines = database.readlines()
        database_movies = []
        database_ids = []
        for line in database_lines:
            database_movies.append(line[:line.find("|") - 1].lower().strip())
            database_ids.append(line[:line.find("|")].replace("?", "|").lower().strip())
            # print(line[:line.find("|") - 1].lower().strip())

        index = 0
        for movie in movies:
            if movie.lower().strip().replace("\n", "") in database_movies:
                movies[index] += "?"

            index += 1

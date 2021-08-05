import requests
from xml.etree import ElementTree
import xmltodict
import os

IN_OUT_DIR = r'A:\Software Dev\Projects\RandomPlay\Random-Play\app\src\main\res\raw\m_animated_disney.txt'
DATABASE_DIR = r'A:\Software Dev\Projects\RandomPlay\Random-Play\app\src\main\python\netflix_helper\out\NetflixMovieData.txt'

with open(IN_OUT_DIR, 'r+') as in_movies:
    with open(DATABASE_DIR, 'r') as database:
        
        movies = in_movies.readlines()
        
        database_lines = database.readlines()
        database_movies = []
        database_ids = {}
        for line in database_lines:
            name = line[:line.find("|") - 1].lower().strip()
            movie_id = line[line.find("|") + 5:].replace("\n", "").strip()
            database_movies.append(name)
            database_ids.setdefault(name, movie_id)


        index = 0
        for movie in movies:
            name = movie.lower().strip().replace("\n", "")
            if name in database_movies:
                movies[index] = (name.title() + " ?" + database_ids[name] + '\n')
                print(movies[index])
            index += 1
        
        in_movies.close()
        os.remove(IN_OUT_DIR)
        out = open(IN_OUT_DIR, "a")
        out.writelines(movies)
        out.flush
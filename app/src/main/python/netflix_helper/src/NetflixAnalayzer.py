import json
import os

BASE_DIR = r'A:\Software Dev\Projects\RandomPlay\Random-Play\app\src\main\python\netflix_helper'

IN_FILE_DIR = os.path.join(BASE_DIR,  'raw', 'raw_response.json')
MOVIE_OUT_FILE_DIR = os.path.join(BASE_DIR, 'out', 'NetflixMovieData.txt')
SERIES_OUT_FILE_DIR = os.path.join(BASE_DIR, 'out', 'NetflixSeriesData.txt')

def write():
    with open(IN_FILE_DIR, 'r', encoding="utf8") as raw:

        out_file_movies = open(MOVIE_OUT_FILE_DIR, 'a')

        out_file_series = open(SERIES_OUT_FILE_DIR, 'a')

        data = json.load(raw)
        for i in data['jsonGraph']['lists']:
            temp = list(data['jsonGraph']['lists'][i].keys())
            for val in temp:
                try:

                    num = int(val)
                    info_dict = data['jsonGraph']['lists'][i][str(num)]['itemSummary']['value']
                    if info_dict['type'] == 'movie':
                        out_file_movies.write(info_dict['title'] + " | Id: " + str(info_dict['videoId']) + '\n')
                    else:
                        out_file_series.write(info_dict['title'] + " | Id: " + str(info_dict['videoId']) + '\n')

                except (ValueError, KeyError):
                    pass
        
        raw.close()
        out_file_movies.close()
        out_file_series.close()


def remove_dups(file_dir):
    with open(file_dir, 'r') as f:
        lines = f.readlines()
        new_lines = lines.copy()
        for i in range(len(lines)):
            if lines.count(lines[i]) > 1:
                value = lines[i]
                done = False
                while not done:
                    try:
                        new_lines.remove(value)
                    except ValueError:
                        done = True
                        new_lines.append(value)
    
        f.close()
        if len(lines) != len(new_lines):
            os.remove(file_dir)
            f = open(file_dir, 'w')
            f.writelines(new_lines)


if __name__ == "__main__":
    write()
    remove_dups(MOVIE_OUT_FILE_DIR)
    remove_dups(SERIES_OUT_FILE_DIR)

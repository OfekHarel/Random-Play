import json
import os

IN_FILE_DIR = r'raw/raw_response.json'
MOVIE_OUT_FILE_DIR = r'out/NetflixMovieData.txt'
SERIES_OUT_FILE_DIR = r'out/NetflixSeriesData.txt'


with open(IN_FILE_DIR, 'r', encoding="utf8") as raw:
    m_open_out = False
    s_open_out = False
    while not s_open_out and not m_open_out:
        try:
            out_file_movies = open(MOVIE_OUT_FILE_DIR, 'a')
            m_open_out = True

            out_file_series = open(SERIES_OUT_FILE_DIR, 'a')
            s_open_out = True
        except FileExistsError:
            if not m_open_out:
                os.remove(MOVIE_OUT_FILE_DIR)
            if not s_open_out:
                os.remove(SERIES_OUT_FILE_DIR)

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
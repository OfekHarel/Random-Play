import os

PATH = r'A:\Software Dev\Projects\RandomPlay\Random-Play\app\src\main\res\raw\s_games_of_thrones.txt'

def filterd_lines():
    filterd = []
    with open(PATH, 'r') as raw_file:
        lines = raw_file.readlines()
        for line in lines:
            if line.strip() == '' or line.split()[0].lower().startswith('season'):
                pass
            else:
                start = line.find('"') + 1
                end = line[start:].find('"') + start
                line = line [start: end]
            
            line = line.replace('\n', '')
            filterd.append(f'{line}\n')
    
    return filterd

if __name__ == '__main__':
    to_write = filterd_lines()
    os.remove(PATH)
    with open(PATH, 'a') as filterd_file:
        filterd_file.writelines(to_write)

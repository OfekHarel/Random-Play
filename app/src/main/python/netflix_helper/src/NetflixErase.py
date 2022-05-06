import os

BASE_ROOT = r'A:\Software Dev\Projects\RandomPlay\Random-Play\app\src\main\res\raw'
EXIT = "q"

sstr = f"Type in full file name or '{EXIT}' to exit: "
filename = input(sstr)
while filename != EXIT:
    new_lines = []
    path = os.path.join(BASE_ROOT, f'{filename}.txt')

    with open(path, 'r') as f:
        lines = f.readlines()
        for line in lines:
            if line.find("|") != -1:
                line = line.split("|")[:1][0][:-1]
            line = line.replace("\n", "")
            new_lines.append(line + '\n')
        f.close()
    
    os.remove(path)
    with open(path, 'a') as f:
        f.writelines(new_lines)
        f.close()
    print("done")

    filename = input(sstr)
    
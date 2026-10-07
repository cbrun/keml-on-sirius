import workbook_analyser
import workbook_editor
import os
import sys

def main():
    user_dir_path = ""
    file_name = ""
    if len(sys.argv) != 3:
        raise TypeError("missing path argument")
    else:
        user_dir_path = sys.argv[1]
        file_name = sys.argv[2]
    if os.path.isdir(user_dir_path):
        if not os.path.exists(f'{user_dir_path}/stats_{file_name}'):
            os.mkdir(f'{user_dir_path}/stats_{file_name}')
        for ana_file in os.listdir(user_dir_path):     
            if os.path.isdir(f'{user_dir_path}/{ana_file}') or os.path.splitext(ana_file)[1] == ".csv" or not ana_file.startswith(file_name):
                continue
            f_s = os.path.join(user_dir_path, ana_file)          
            h_t = f'{user_dir_path}/stats_{file_name}/{os.path.splitext(ana_file)[0]}'
            workbook_editor.insert_rand_values(f_s)
            workbook_analyser.run_wb_analysis(f_s, h_t)

if __name__ == '__main__':
	main()

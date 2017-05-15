import os
import subprocess


class Operating_System:
	Windows = 1
	Linux = 2

def determine_platform():
    if os.name == 'nt':
        return Operating_System.Windows
    elif os.name == 'posix':
        return Operating_System.Linux

def main():
    operating_sys = determine_platform()
    script_name = ''
    if operating_sys == Operating_System.Windows:
		script_name = "run_bat.bat"
    elif operating_sys == Operating_System.Linux:
	    script_name = "./run_bash.sh"
    else:
		print "Operating system is not recognized."
		return
		
    p = subprocess.Popen(script_name, shell=True, stdout = subprocess.PIPE)
    stdout, stderr = p.communicate()
    print p.returncode


if __name__ == '__main__':
    main()

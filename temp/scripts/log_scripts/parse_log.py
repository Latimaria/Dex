#!/usr/bin/env python3
import re
import csv

base_path = "/Users/qing/Courses/Android/ASM/temp/scripts/"
logfile = base_path + "logs/OG_log.txt"
outfile = base_path + "log_scripts/output/OG_log.csv"

# Define the regular expression patterns to match the log entries
before_pattern = r'\[BM\] \[BEFORE\] Duration: (\d+(?:\.\d+)?) ms'
after_pattern = r'\[BM\] \[AFTER\] Duration: (\d+(?:\.\d+)?) ms'

# Open the log file
with open(logfile, 'r') as log_file:
    # Read the contents of the log file
    log_content = log_file.read()

    # Find all the matches for the before and after patterns
    before_matches = re.findall(before_pattern, log_content)
    after_matches = re.findall(after_pattern, log_content)

    print(len(before_matches))
    print(len(after_matches))
    # Create a list of tuples containing the before and after durations
    durations = [(float(before), float(after)) for before, after in zip(before_matches, after_matches)]

# Write the durations to a CSV file


with open(outfile, 'w', newline='') as csvfile:
    writer = csv.writer(csvfile)
    writer.writerow(['Before (ms)', 'After (ms)'])
    writer.writerows(durations)

print("saved to:")
print(outfile)
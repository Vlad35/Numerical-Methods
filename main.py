# Open the input file for reading
with open("dataset_1.txt", "r") as input_file:

# Open the output file for writing
    with open("updated_dataset_1.txt", "w") as output_file:
        next(input_file)
        next(input_file)

# Loop through each line in the input file
        for line in input_file:

# Split the line into columns
            columns = line.split()

# Check if the line has at least 3 columns
            if len(columns) >= 3:

# Write the third column to the output file
                output_file.write(columns[2] + "\n")

# Open the input file for reading
with open("updated_dataset_1.txt", "r") as input_file:
# Loop through the lines and calculate the average
    total = 0
    count = 0
    for line in input_file:
        value = float(line.strip())
        total += value
        count += 1
    average = total / count

# Reopen the new output file for reading and writing
with open("updated_dataset_1.txt", "r+") as input_file:
# Loop through the lines and subtract the average
    for line in input_file:
        value = float(line.strip())
        new_value = value - average
        input_file.seek(-len(line), 1)
        input_file.write(str(new_value) + "\n")
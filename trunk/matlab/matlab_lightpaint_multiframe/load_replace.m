function output = load_replace(filename)
% Loads an SVG file and exports the coordinates of each line endpoint
% contained in the order x1 y1 x2 y2
% All non line elements of the SVG file are erased through some awesome
% regular expressions. ONLY single lines (not polygons or paths)

% Load file as string
file_str = fileread(filename);

% Remove everything that's not a line
data = regexprep(file_str, '^((?![\s]+<line).*)$', '', 'dotexceptnewline', 'lineanchors');

% Remove everything that's not a coordinate or coordinate label
m = regexp(data, '([xy][12]="[0-9.]+" ){4}', 'match');

% Separate string data for each line by coordinate label
n_lines = size(m,2);
output = zeros(n_lines,4);
for i = 1:n_lines
    m(i) = regexprep(m(i), '[=" ]+', '\t');
	[data] = textscan(char(m(i)), '%s %d');
    values = cell2mat(data(2));
    output(i,1) = values(find(strncmp('x1',data{1},2)));
    output(i,2) = values(find(strncmp('y1',data{1},2)));
    output(i,3) = values(find(strncmp('x2',data{1},2)));
    output(i,4) = values(find(strncmp('y2',data{1},2)));
end
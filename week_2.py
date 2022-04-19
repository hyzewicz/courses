#! /usr/bin/env python3

"""Post a JSON file created from a given folder of files"""

import os
import requests
from itertools import chain

keys = ["id", "title", "name", "date", "feedback"]
id = 2

for file in os.listdir("/data/feedback"):
	with open("/data/feedback/" + file) as current_file:
		lines = current_file.readlines()
	lines_1 = []
	for line in lines:
		lines_1.append(line.strip())
	list_id = [id]
	values = list(chain(list_id, lines_1))
	zip_obj = zip(keys, values)
	current_dict = dict(zip_obj)
	review = requests.post("input website", json = current_dict)
	print("status code {}".format(review.status_code))
	id += 1
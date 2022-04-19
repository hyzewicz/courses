#!/usr/bin/env python3

"""Process a specific range of files from given folder"""

import os
import re
from PIL import Image

for file in os.listdir("images"):
    file_name = file
    if re.search(r"^im_", file):
        im = Image.open("images/" + file)
        new_im = im.rotate(270).resize((128,128)).convert("RGB")
        new_im.save("new_images" + file_name, "jpeg")

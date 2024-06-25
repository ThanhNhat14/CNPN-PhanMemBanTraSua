import os
from PIL import Image

folder_path = "C:\\Users\\ngocn\\OneDrive\\Desktop\\_\\Java\\AppTraSua\\src\\main\\resources\\img\\products"

# Lấy danh sách các file trong thư mục
file_list = os.listdir(folder_path)

# Lặp qua từng file và lấy tên và phần đuôi
for file_name in file_list:
    base_name = os.path.basename(file_name)


    

    # Đường dẫn đến ảnh
    image_path = base_name

    # Đọc ảnh
    image = Image.open(image_path)

    # Kích thước mới (ở đây là 800x600)
    new_size = (120, 120)

    # Thu nhỏ kích thước ảnh
    resized_image = image.resize(new_size)

    # Lưu ảnh thu nhỏ
    resized_image.save(base_name)

    print("Đã thay đổi kích thước ảnh thành công.")
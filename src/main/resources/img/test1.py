from PIL import Image

# Đường dẫn đến ảnh
image_path = 'Rick.png'

# Đọc ảnh
image = Image.open(image_path)

# Kích thước mới (ở đây là 800x600)
new_size = (325, 325)

# Thu nhỏ kích thước ảnh
resized_image = image.resize(new_size)

# Lưu ảnh thu nhỏ
resized_image.save('Rick.png')

print("Đã thay đổi kích thước ảnh thành công.")
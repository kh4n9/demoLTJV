package Nhom1.demo.entities; // Định nghĩa package cho class

import java.nio.charset.StandardCharsets; // Import class StandardCharsets để sử dụng các đối tượng Charset đã định nghĩa sẵn
import java.security.MessageDigest; // Import class MessageDigest để sử dụng các thuật toán băm
import java.security.NoSuchAlgorithmException; // Import class NoSuchAlgorithmException để xử lý các ngoại lệ liên quan đến thuật toán băm

public class Hash { // Định nghĩa class Hash

    // Hàm thực hiện thuật toán MD5 trên một chuỗi đầu vào
    public String computeMD5(String originalString) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5"); // Tạo đối tượng MessageDigest với thuật toán MD5
            byte[] encodedhash = digest.digest(originalString.getBytes(StandardCharsets.UTF_8)); // Băm chuỗi đầu vào và lưu kết quả vào mảng byte
            return bytesToHex(encodedhash); // Chuyển đổi mảng byte thành chuỗi hex và trả về
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e); // Nếu không tìm thấy thuật toán, ném ra ngoại lệ
        }
    }

    // Hàm thực hiện thuật toán SHA-256 trên một chuỗi đầu vào
    public String computeSHA256(String originalString) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256"); // Tạo đối tượng MessageDigest với thuật toán SHA-256
            byte[] encodedhash = digest.digest(originalString.getBytes(StandardCharsets.UTF_8)); // Băm chuỗi đầu vào và lưu kết quả vào mảng byte
            return bytesToHex(encodedhash); // Chuyển đổi mảng byte thành chuỗi hex và trả về
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e); // Nếu không tìm thấy thuật toán, ném ra ngoại lệ
        }
    }

    // Hàm chuyển đổi một mảng byte thành một chuỗi hex
    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length); // Tạo đối tượng StringBuilder để xây dựng chuỗi hex
        for (int i = 0; i < hash.length; i++) { // Duyệt qua từng byte trong mảng
            String hex = Integer.toHexString(0xff & hash[i]); // Chuyển đổi byte thành số hex
            if (hex.length() == 1) {
                hexString.append('0'); // Nếu số hex chỉ có một chữ số, thêm số 0 vào đầu
            }
            hexString.append(hex); // Thêm số hex vào chuỗi
        }
        return hexString.toString(); // Trả về chuỗi hex
    }

    public String addSalt(String salt, String password) {
        return salt + password; // Kết hợp muối và mật khẩu thành một chuỗi
    }
}
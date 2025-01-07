
package com.example.PandaCoffee.service;
import com.example.PandaCoffee.dto.request.SendEmailRequest;
import com.example.PandaCoffee.dto.request.UserRequest;
import com.example.PandaCoffee.dto.response.To;
import com.example.PandaCoffee.dto.response.UserResponse;
import com.example.PandaCoffee.mapper.UserMapper;
import com.example.PandaCoffee.model.User;
import com.example.PandaCoffee.repositories.UserRepository;
import com.example.PandaCoffee.service.CodeConfirmEmailService;
import com.example.PandaCoffee.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    CodeConfirmEmailService codeConfirmEmailService;

    @Autowired
    EmailService emailService;

    @Autowired
    CloudinaryService cloudinaryService;

    @Autowired
    private UserMapper userMapper;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

//    public User createUser(UserRequest userRequest) {
//        var user = userMapper.toUser(userRequest);
//        return userRepository.save(user);
//    }

    public UserResponse addUser(UserRequest userRequest) throws IOException {
        // Kiểm tra email đã tồn tại
        if (userRepository.findUserByEmail(userRequest.getEmail()) != null) {
            throw new IllegalArgumentException("Email đã tồn tại trong hệ thống.");
        }

        User user = userMapper.toUser(userRequest);
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setStatus(false); // Chưa xác thực

        // Random mã xác thực
        String verificationCode = String.format("%06d", (int) (Math.random() * 1000000));
        user.setVerificationCode(verificationCode);
//        user.setCodeExpiration(LocalDateTime.now().plusMinutes(15)); // Hết hạn sau 15 phút

        userRepository.save(user);

        // Gửi email
        emailService.sendEmail(SendEmailRequest.builder()
                .to(To.builder()
                        .email(user.getEmail())
                        .name(user.getName())
                        .build())
                .htmlContent(String.format(
                        "<p>Chào %s,</p><p>Mã xác thực tài khoản của bạn là: <b>%s</b></p>",
                        user.getName(), verificationCode))
                .subject("Mã xác thực tài khoản PandaCoffee")
                .build());

        return userMapper.toUserResponse(user);
    }

    //Xac thuc ma
    public boolean verifyUser(String email, String code) {
        // Kiểm tra xem email có tồn tại và mã code có hợp lệ không
        User user = userRepository.findUserByEmail(email);

        if (user.getVerificationCode().equals(code)) {
            // Nếu email và mã code hợp lệ, trả về true
            return true;
        } else {
            // Nếu không hợp lệ, trả về false
            return false;
        }
    }




    public UserResponse findUserById(int id){
        User user = userRepository.findById(id).get();
        return userMapper.toUserResponse(user);
    }
//
//    public UserResponse findUserByUserName(String username){
//        User user = userRepository.findUserByEmail(username);
//        return userMapper.toUserResponse(user);
//    }

    public UserResponse myInfo(){
        var accountCurrent = SecurityContextHolder.getContext().getAuthentication();
        return userMapper.toUserResponse(userRepository.findUserByEmail(accountCurrent.getName()));
    }

    //Chinh sua profile
    public UserResponse updateProfile(int userId, UserRequest userRequest, MultipartFile file) throws IOException {
        // Tìm kiếm người dùng theo ID
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Người dùng không tồn tại với ID: " + userId));

        // Kiểm tra nếu email đã tồn tại trên hệ thống và không thuộc về user hiện tại
        if (!user.getEmail().equals(userRequest.getEmail()) &&
                userRepository.findUserByEmail(userRequest.getEmail()) != null) {
            throw new IllegalArgumentException("Email đã tồn tại trong hệ thống.");
        }

        // Cập nhật thông tin người dùng
        userMapper.updateUserFromRequest(userRequest, user);

        // Kiểm tra và xử lý avatar
        if (file != null && !file.isEmpty()) {
            // Lưu hoặc cập nhật hình ảnh avatar của người dùng

            if (!file.isEmpty()) {
                user.setAvatar(cloudinaryService.uploadImage(file));
            } else {
                user.setAvatar(null);
            }
        }

        // Lưu thông tin người dùng đã cập nhật
        User updatedUser = userRepository.save(user);

        // Trả về response sau khi cập nhật
        return userMapper.toUserResponse(updatedUser);
    }


}

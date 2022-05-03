package ArtShoppingApplication.services;

import ArtShoppingApplication.exceptions.*;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import ArtShoppingApplication.model.User;


import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import static ArtShoppingApplication.services.FileSystemService.getPathToFile;
import static org.dizitart.no2.objects.filters.ObjectFilters.eq;

public class UserService {

    private static ObjectRepository<User> userRepository;


    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("registered-clients.db").toFile())
                .openOrCreate("test", "test");
        userRepository = database.getRepository(User.class);
    }

    public static void addUser(String username, String password, String fullName, String address, String email, String role) throws  EmailAlreadyRegistered, UsernameFieldEmptyException, PasswordFieldEmptyException, AddressFieldEmptyException, FullNameFieldEmptyException, EmailFieldEmptyException, RoleFieldEmptyException {

        checkUserFieldIsNotEmpty(username);
        checkPasswordFieldIsNotEmpty(password);
        checkFullNameFieldIsNotEmpty(fullName);
        checkAddressFieldIsNotEmpty(address);
		checkEmailFieldIsNotEmpty(email);
		checkEmailAlreadyRegistered(email);
        checkSelectedRole(role);
        userRepository.insert(new User(username, encodePassword(email, password), fullName, address, email, role));

    }



	private static void checkEmailAlreadyRegistered(String email) throws EmailAlreadyRegistered {
        for (User user : userRepository.find()) {
            if (Objects.equals(email, user.getEmail()))
                throw new EmailAlreadyRegistered(email);
        }
    }

    private static void checkUserFieldIsNotEmpty(String username) throws UsernameFieldEmptyException{
        if(username.equals(""))
            throw new UsernameFieldEmptyException();
    }

	 private static void checkPasswordFieldIsNotEmpty(String password) throws PasswordFieldEmptyException{
        if(password.equals(""))
            throw new PasswordFieldEmptyException();
    }
    
	 private static void checkAddressFieldIsNotEmpty(String address) throws AddressFieldEmptyException{
        if(address.equals(""))
            throw new AddressFieldEmptyException();
    }

	 private static void checkEmailFieldIsNotEmpty(String password) throws EmailFieldEmptyException{
        if(password.equals(""))
            throw new EmailFieldEmptyException();
    }
	 private static void checkFullNameFieldIsNotEmpty(String fullName) throws FullNameFieldEmptyException{
        if(fullName.equals(""))
            throw new FullNameFieldEmptyException();
    }

    private static void checkSelectedRole(String role) throws RoleFieldEmptyException{
        if(role==null)
            throw new RoleFieldEmptyException();
    }
    public static User checkCorrectPassword(String email, String password) throws IncorrectPasswordException, UserDoesNotExist {
        for (User user : userRepository.find()) {
            if (Objects.equals(email, user.getEmail())) {
                if (Objects.equals(encodePassword(email, password), user.getPassword())) {
                    return user;
                } else {
                    throw new IncorrectPasswordException(password);
                }
            }


        }
		 throw new UserDoesNotExist();
    }

    private static String encodePassword(String salt, String password) {
        MessageDigest md = getMessageDigest();
        md.update(salt.getBytes(StandardCharsets.UTF_8));

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        return new String(hashedPassword, StandardCharsets.UTF_8)
                .replace("\"", "");
    }

    private static MessageDigest getMessageDigest() {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-512 does not exist!");
        }
        return md;
    }
    public static void deleteSeller(String email) {
        // userRepository.remove(ObjectFilters.ALL); //used because of nasty first try of creating database
        userRepository.remove(eq("email", email));
    }

    public static int verify(String email, String password) {
        for(User user : userRepository.find()) {
            if(email.equals(user.getEmail())){
                if(encodePassword(email,password).equals(user.getPassword())) {
                    if(user.getRole() == "Seller") {
                        return 1;
                    }
                    else{
                        return 2;
                    }
                }
                else {
                    return 0;
                }
            }
        }
        return -1;
    }
}




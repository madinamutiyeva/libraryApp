package kz.madina.library.controller;

import kz.madina.library.model.User;
import kz.madina.library.service.BookService;
import kz.madina.library.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/user")
public class UserController {
    private final BookService bookService;
    private final UserService userService;

    @Autowired
    public UserController(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
    }

    @GetMapping("/search")
    public String search(@RequestParam String query, @RequestParam Long userId, Model model) {
        User user = userService.findById(userId);
        model.addAttribute("userBooks", user.getBooks());
        model.addAttribute("user", user);
        model.addAttribute("searchResults", bookService.searchBooks(query, userId));
        return "user/main";
    }

    @PostMapping("/add")
    public String add(@RequestParam Long bookId,@RequestParam Long userId, Model model) {
        System.out.println("id: " + bookId);
        User user = userService.findById(userId);
        userService.addBook(bookId, userId);
        model.addAttribute("userBooks", user.getBooks());
        model.addAttribute("user", user);
        model.addAttribute("searchResults", new ArrayList<>());
        return "user/main";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam Long deleteId,@RequestParam Long userId, Model model) {
        User user = userService.findById(userId);
        model.addAttribute("userBooks", user.getBooks());
        model.addAttribute("user", user);
        model.addAttribute("searchResults", new ArrayList<>());
        userService.deleteBook(deleteId, userId);
        return "user/main";
    }
}

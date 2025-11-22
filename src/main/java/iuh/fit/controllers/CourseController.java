package iuh.fit.controllers;

import iuh.fit.dtos.CourseDTO;
import iuh.fit.exceptions.BadRequestException;

import iuh.fit.service.CourseService;
import iuh.fit.service.LecturerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;  // Thêm final
    private final LecturerService lecturerService;  // Thêm final

    @GetMapping
    public String listCourses(Model model) {
        model.addAttribute("courses", courseService.getAllCoursesWithLecturerCount());
        return "list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("courseDTO", new CourseDTO());
        return "form";
    }

    @PostMapping
    public String createCourse(@Valid @ModelAttribute("courseDTO") CourseDTO courseDTO,
                               BindingResult result,
                               RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "form";
        }

        try {
            courseService.createCourse(courseDTO);
            redirectAttributes.addFlashAttribute("message", "Thêm khóa học thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/courses";
    }

    @GetMapping("/{id}/lecturers")
    public String viewLecturers(@PathVariable Integer id, Model model) {
        model.addAttribute("course", courseService.getCourseById(id));
        model.addAttribute("lecturers", lecturerService.getLecturersByCourseId(id));
        return "lecturers";
    }

    @PostMapping("/{id}/delete")
    public String deleteCourse(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            courseService.deleteCourse(id);
            redirectAttributes.addFlashAttribute("message", "Xóa khóa học thành công!");
        } catch (BadRequestException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/courses";
    }
}
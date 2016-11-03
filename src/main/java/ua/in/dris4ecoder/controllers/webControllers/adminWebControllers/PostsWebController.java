package ua.in.dris4ecoder.controllers.webControllers.adminWebControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.in.dris4ecoder.controllers.businessControllers.StaffController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Alex Korneyko on 31.10.2016 19:36.
 */
@Controller
public class PostsWebController {

    @Autowired
    private StaffController staffController;

    @RequestMapping(value = "admin/posts")
    public ModelAndView posts(@RequestParam Map<String, String> params) {

        ModelAndView modelAndView = new ModelAndView("admin/posts");

        if (params.containsKey("create")) {

            modelAndView.addObject("posts", staffController.getAllEmployeePosts());
            modelAndView.addObject("openEditWindow", true);
        }

        if (params.containsKey("edit")) {

            List<String> selected = params.keySet().stream()
                    .filter(key -> key.length() > 8 && key.substring(0, 8).equals("selected"))
                    .collect(Collectors.toList());

            if (selected.size() != 1) {
                modelAndView.addObject("wrongCount", true);
            } else {
                int employeePostId = Integer.parseInt(params.get(selected.get(0)));

                modelAndView.addObject("postNameForEditing", staffController.findEmployeePostById(employeePostId));
                modelAndView.addObject("postIdForEditing", employeePostId);

                modelAndView.addObject("openEditWindow", true);
            }

            modelAndView.addObject("posts", staffController.getAllEmployeePosts());
        }

        if (params.containsKey("newPost")) {

            if (params.get("postId").equals(""))
                staffController.addEmployeePost(params.get("newPost"));
            else
                staffController.editEmployeePost(Integer.parseInt(params.get("postId")), params.get("newPost"));

            modelAndView.addObject("posts", staffController.getAllEmployeePosts());
        }

        if (params.containsKey("delete")) {

            params.keySet()
                    .stream().filter(key -> key.length() > 8 && key.substring(0, 8).equals("selected"))
                    .forEach(key -> staffController.removeEmployeePost(Integer.parseInt(params.get(key))));

            modelAndView.addObject("posts", staffController.getAllEmployeePosts());
        }

        if (params.containsKey("showAll")) {
            modelAndView.addObject("posts", staffController.getAllEmployeePosts());
        }

        return modelAndView;
    }
}

package pl.javaps.shop.admin.category.controller;

import com.github.slugify.Slugify;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.javaps.shop.admin.category.controller.dto.AdminCategoryDto;
import pl.javaps.shop.admin.category.model.AdminCategory;
import pl.javaps.shop.admin.category.service.AdminCategoryService;

import java.util.List;

@RestController
@RequestMapping("/admin/categories")
@RequiredArgsConstructor
public class AdminCategoryController {

    public static final Long EMPTY_ID = null;
    private final AdminCategoryService adminCategoryService;

    @GetMapping
    public List<AdminCategory> getCategories(){
        return adminCategoryService.getCategories();
    }

    @GetMapping("/{id}")
    public AdminCategory getCategory(@PathVariable Long id){
        return adminCategoryService.getCategory(id);
    }

    @PostMapping
    public AdminCategory createCategory(@RequestBody AdminCategoryDto adminCategoryDto){

        return adminCategoryService.createCategory(mapToAdmincategory(EMPTY_ID, adminCategoryDto));
    }

    @PutMapping("/{id}")
    public AdminCategory updateCategory(@PathVariable Long id, @RequestBody AdminCategoryDto adminCategoryDto){
        return adminCategoryService.updateCategory(mapToAdmincategory(id, adminCategoryDto));
    }


    @DeleteMapping("/{id}")
    public void deleteCategory(long id){
        adminCategoryService.deleteCategory(id);
    }

    private AdminCategory mapToAdmincategory(Long id, AdminCategoryDto adminCategoryDto) {
        return AdminCategory.builder()
                .id(adminCategoryDto.getId())
                .name(adminCategoryDto.getName())
                .description(adminCategoryDto.getDescription())
                .slug(slugifyCategoryName(adminCategoryDto.getSlug()))
                .build();
    }

    private String slugifyCategoryName(String slug) {
        Slugify slugify = new Slugify();
        return slugify.withCustomReplacement("_","-").slugify(slug);
    }


}

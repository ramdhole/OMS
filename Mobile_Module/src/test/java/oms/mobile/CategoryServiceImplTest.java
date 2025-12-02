package com.onlineapplication;
 
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
 
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
 
import com.onlineapplication.dto.CategoryDto;
import com.onlineapplication.entity.Category;
import com.onlineapplication.entity.Mobile;
import com.onlineapplication.exception.CategoryException;
import com.onlineapplication.repository.CategoryRepository;
import com.onlineapplication.repository.MobileRepository;
import com.onlineapplication.service.CategoryServiceImpl;
 
@ExtendWith(MockitoExtension.class)
public class CategoryServiceImplTest {
 
    @Mock
    private MobileRepository mobileRepository;
 
    @Mock
    private CategoryRepository categoryRepository;
 
    @Mock
    private ModelMapper modelMapper;
 
    @InjectMocks
    private CategoryServiceImpl categoryService;
 
    private Category category;
    private CategoryDto categoryDto;
    private Mobile mobile;
 
    @BeforeEach
    public void setUp() {
        category = new Category();
        category.setCategoryId(1);
        category.setCategoryName("Test Category");
 
        categoryDto = new CategoryDto();
        categoryDto.setCategoryId(1);
        categoryDto.setCategoryName("Test Category");
 
        mobile = new Mobile();
        mobile.setMobileId(1L);
        mobile.setModelNumber("ABC123");
        mobile.setMobileName("Test Mobile");
        mobile.setMobileRam(8);
        mobile.setMobileCost(500.0f);
        mobile.setAvailableQuantity(10);
        mobile.setCategory(category);
    }
 
    @Test
    public void testCreateCategory() {
        when(categoryRepository.save(any(Category.class))).thenReturn(category);
        when(modelMapper.map(any(Category.class), any())).thenReturn(categoryDto);
 
        CategoryDto result = categoryService.createCategory(categoryDto);
 
        assertEquals(categoryDto.getCategoryName(), result.getCategoryName());
    }
 
    @Test
    public void testGetAllCategoriesName() {
        List<Category> categories = new ArrayList<>();
        categories.add(category);
        when(categoryRepository.findAll()).thenReturn(categories);
 
        List<String> result = categoryService.getAllCategoriesName();
 
        assertEquals(1, result.size());
        assertEquals(category.getCategoryName(), result.get(0));
    }
 
    @Test
    public void testGetCategoryById() {
        when(categoryRepository.findById(any(Integer.class))).thenReturn(Optional.of(category));
        when(modelMapper.map(any(Category.class), any())).thenReturn(categoryDto);
 
        CategoryDto result = categoryService.getCategoryById(1);
 
        assertEquals(categoryDto.getCategoryName(), result.getCategoryName());
    }
 
    @Test
    public void testGetCategoryById_CategoryNotFoundException() {
        when(categoryRepository.findById(any(Integer.class))).thenReturn(Optional.empty());
 
        assertThrows(CategoryException.class, () -> categoryService.getCategoryById(1));
    }
 
 
    @Test
    public void testGetMobilesByCategoryId_CategoryNotFoundException() {
        when(categoryRepository.findById(any(Integer.class))).thenReturn(Optional.empty());
 
        assertThrows(CategoryException.class, () -> categoryService.getMobilesByCategoryId(1));
    }
 
    @Test
    public void testDeleteCategoryById() {
        when(categoryRepository.findById(any(Integer.class))).thenReturn(Optional.of(category));
        when(modelMapper.map(any(Category.class), any())).thenReturn(categoryDto);
 
        CategoryDto result = categoryService.deleteCategoryById(1);
 
        assertEquals(categoryDto.getCategoryName(), result.getCategoryName());
    }
 
    @Test
    public void testDeleteCategoryById_CategoryNotFoundException() {
        when(categoryRepository.findById(any(Integer.class))).thenReturn(Optional.empty());
 
        assertThrows(CategoryException.class, () -> categoryService.deleteCategoryById(1));
    }
 
 
    @Test
    public void testGetMobilesByCategoryName_CategoryNotFoundException() {
        when(categoryRepository.findByCategoryName(any(String.class))).thenReturn(null);
 
        assertThrows(CategoryException.class, () -> categoryService.getMobilesByCategoryName("Test Category"));
    }
}
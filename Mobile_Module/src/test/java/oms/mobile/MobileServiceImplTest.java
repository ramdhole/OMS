package oms.mobile;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
 
import java.util.ArrayList;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import oms.mobile.entity.Category;
import oms.mobile.entity.Mobile;
import oms.mobile.exception.CategoryException;
import oms.mobile.exception.MobileNotFoundException;
import oms.mobile.exception.MobilesException;
import oms.mobile.repository.CategoryRepository;
import oms.mobile.repository.MobileRepository;
import oms.mobile.service.MobileServiceImpl;

@ExtendWith(MockitoExtension.class)
public class MobileServiceImplTest {
    @Mock
    private MobileRepository mobileRepository;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private MobileServiceImpl mobileService;
    private Mobile mobile;
    private Category category;
    @BeforeEach
    public void setUp() {
        category = new Category();
        category.setCategoryId(1);
        category.setCategoryName("Smartphones");
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
        public void testFindByMobileCostGreaterThan_NoMobileFound() {
            when(mobileRepository.findByMobileCostGreaterThan(any(Float.class))).thenReturn(new ArrayList<>());
            Assertions.assertThrows(MobileNotFoundException.class, () -> mobileService.findByMobileCostGreaterThan(600.0f));
        }
    @Test
    public void testAddMobile_CategoryNotFoundException() {
        when(categoryRepository.findByCategoryName(any(String.class))).thenReturn(null);
        assertThrows(CategoryException.class, () -> mobileService.addMobile(mobile));
    }

    @Test
    public void testUpdateMobile_MobileNotFoundException() {
        when(categoryRepository.findByCategoryName(any(String.class))).thenReturn(category);
        when(mobileRepository.findById(any(Long.class))).thenReturn(Optional.empty());
        assertThrows(MobilesException.class, () -> mobileService.updateMobile(mobile));
    }
    @Test
    public void testDeleteMobile_MobileNotFoundException() {
        when(mobileRepository.findById(any(Long.class))).thenReturn(Optional.empty());
        assertThrows(MobileNotFoundException.class, () -> mobileService.deleteMobile(1L));
    }
    @Test
    public void testShowMobileById_MobileNotFoundException() {
        when(mobileRepository.findById(any(Long.class))).thenReturn(Optional.empty());
        assertThrows(MobileNotFoundException.class, () -> mobileService.showMobileById(1L));
    }
    @Test
    public void testUpdateMobile_CategoryNotFound() {
        when(categoryRepository.findByCategoryName(any(String.class))).thenReturn(null);
        Assertions.assertThrows(CategoryException.class, () -> mobileService.updateMobile(mobile));
    }
    @Test
    public void testUpdateMobile_MobileNotFound() {
        when(categoryRepository.findByCategoryName(any(String.class))).thenReturn(category);
        when(mobileRepository.findById(any(Long.class))).thenReturn(Optional.empty());
        Assertions.assertThrows(MobilesException.class, () -> mobileService.updateMobile(mobile));
    }
    @Test
    public void testDeleteMobile_MobileNotFound() {
        when(mobileRepository.findById(any(Long.class))).thenReturn(Optional.empty());
        Assertions.assertThrows(MobileNotFoundException.class, () -> mobileService.deleteMobile(1L));
    }
}
import io.mockk.unmockkAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

open class BaseTest() {
    @AfterEach
    fun refreshData() {
        unmockkAll()
    }
}
// ========================================
// 1. ? (Nullable Type)
// ========================================
fun nullableTypeExample() {
    println("=== Nullable Type (?) ===")
    
    var name: String = "park"  // Non-null
    // name = null  // 컴파일 에러!
    
    var nullableName: String? = "park"  // Nullable
    nullableName = null  // OK
    
    println("Name: $name")
    println("Nullable Name: $nullableName")
}

// ========================================
// 2. ?. (Safe Call Operator)
// ========================================
data class User(val name: String, val email: String?)

fun safeCallExample() {
    println("\n=== Safe Call (?.) ===")
    
    val user1: User? = User("park", "park@example.com")
    val user2: User? = null
    
    // Safe call - null이면 전체 표현식이 null
    println("User1 email length: ${user1?.email?.length}")
    println("User2 email length: ${user2?.email?.length}")
    
    // 체이닝 가능
    val emailUpperCase = user1?.email?.uppercase()?.substring(0, 4)
    println("Email prefix: $emailUpperCase")
}

// ========================================
// 3. !! (Null Assertion Operator)
// ========================================
fun nullAssertionExample() {
    println("\n=== Null Assertion (!!) ===")
    
    val name: String? = "park"
    
    // !! 사용 - null이면 NPE 발생
    val length: Int = name!!.length  // 확신할 때만 사용
    println("Name length: $length")
    
    // 위험한 사용 예시
    try {
        val nullName: String? = null
        val badLength = nullName!!.length  // NPE 발생!
    } catch (e: NullPointerException) {
        println("NPE 발생: ${e.message}")
    }
}

// ========================================
// 4. Smart Casting
// ========================================
fun smartCastingExample() {
    println("\n=== Smart Casting ===")
    
    val name: String? = "park"
    
    // null 체크 후 자동으로 non-null 타입으로 캐스팅
    if (name != null) {
        println("Name length: ${name.length}")  // name은 String으로 smart cast
        println("Uppercase: ${name.uppercase()}")
    }
    
    // when 표현식에서도 작동
    val result = when (name) {
        null -> "No name"
        else -> "Name is ${name.length} characters"  // smart cast
    }
    println(result)
}

fun smartCastWithReturn(name: String?) {
    println("\n=== Smart Cast with Early Return ===")
    
    // early return 패턴
    if (name == null) {
        println("Name is null, returning...")
        return
    }
    
    // 이 시점부터 name은 non-null로 smart cast
    println("Name: $name")
    println("Length: ${name.length}")
    println("First char: ${name[0]}")
}

// ========================================
// 5. ?: (Elvis Operator)
// ========================================
fun elvisOperatorExample() {
    println("\n=== Elvis Operator (?:) ===")
    
    val name: String? = null
    
    // null이면 기본값 사용
    val displayName = name ?: "Guest"
    println("Display name: $displayName")
    
    // 함수 호출과 함께
    val length = name?.length ?: 0
    println("Name length: $length")
    
    // early return과 함께
    fun processUser(user: User?) {
        val validUser = user ?: run {
            println("User is null, returning...")
            return
        }
        println("Processing user: ${validUser.name}")
    }
    
    processUser(null)
    processUser(User("park", "test@test.com"))
}

// ========================================
// 6. lateinit
// ========================================
class Service {
    // lateinit - 나중에 초기화할 것을 약속
    // var + non-null 타입에만 사용 가능
    lateinit var database: Database
    lateinit var config: Config
    
    fun initialize() {
        database = Database("localhost")
        config = Config("production")
    }
    
    fun doSomething() {
        // lateinit이 초기화되었는지 확인
        if (::database.isInitialized) {
            println("Database: ${database.url}")
        } else {
            println("Database not initialized yet!")
        }
        
        if (::config.isInitialized) {
            println("Config: ${config.environment}")
        }
    }
}

data class Database(val url: String)
data class Config(val environment: String)

fun lateinitExample() {
    println("\n=== lateinit ===")
    
    val service = Service()
    service.doSomething()  // 아직 초기화 안됨
    
    service.initialize()
    service.doSomething()  // 초기화됨
}

// ========================================
// 실전 예제: Repository 패턴
// ========================================
data class Product(val id: Int, val name: String, val price: Int?)

class ProductRepository {
    private val products = listOf(
        Product(1, "Laptop", 1000),
        Product(2, "Mouse", null),  // 가격 미정
        Product(3, "Keyboard", 50)
    )
    
    fun findById(id: Int): Product? {
        return products.find { it.id == id }
    }
    
    fun getProductDisplay(id: Int): String {
        // 여러 null safety 기법 조합
        val product = findById(id) ?: return "Product not found"
        
        val priceDisplay = product.price?.let { "$$it" } ?: "Price TBD"
        
        return "${product.name} - $priceDisplay"
    }
}

fun practicalExample() {
    println("\n=== Practical Example ===")
    
    val repo = ProductRepository()
    
    println(repo.getProductDisplay(1))  // Laptop - $1000
    println(repo.getProductDisplay(2))  // Mouse - Price TBD
    println(repo.getProductDisplay(99)) // Product not found
}

// ========================================
// let, run, also, apply with null safety
// ========================================
fun scopeFunctionsWithNull() {
    println("\n=== Scope Functions with Null ===")
    
    val user: User? = User("park", "park@example.com")
    
    // let - null이 아닐 때만 실행
    user?.let {
        println("User name: ${it.name}")
        println("Email: ${it.email}")
    }
    
    // run - null이 아닐 때만 실행 + this로 접근
    val emailLength = user?.run {
        println("Processing user: $name")
        email?.length ?: 0
    }
    println("Email length: $emailLength")
    
    // also - side effect 처리
    val processedUser = user?.also {
        println("Logging user: ${it.name}")
    }
}

// ========================================
// Main
// ========================================
fun main() {
    nullableTypeExample()
    safeCallExample()
    nullAssertionExample()
    smartCastingExample()
    smartCastWithReturn("park")
    smartCastWithReturn(null)
    elvisOperatorExample()
    lateinitExample()
    practicalExample()
    scopeFunctionsWithNull()
}
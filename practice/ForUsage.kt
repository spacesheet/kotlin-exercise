// 1. 커스텀 컬렉션 클래스 (Iterable 구현)
class NumberRange(private val start: Int, private val end: Int) : Iterable<Int> {
    
    // operator 제어자를 붙인 iterator() 메서드
    override operator fun iterator(): Iterator<Int> {
        return NumberRangeIterator(start, end)
    }
    
    // 내부 Iterator 구현
    private class NumberRangeIterator(
        private val start: Int,
        private val end: Int
    ) : Iterator<Int> {
        private var current = start
        
        override fun hasNext(): Boolean {
            return current <= end
        }
        
        override fun next(): Int {
            if (!hasNext()) {
                throw NoSuchElementException()
            }
            return current++
        }
    }
}

// 2. 좀 더 실용적인 예제 - 짝수만 반환하는 컬렉션
class EvenNumbers(private val limit: Int) : Iterable<Int> {
    
    override operator fun iterator(): Iterator<Int> {
        return object : Iterator<Int> {
            private var current = 0
            
            override fun hasNext(): Boolean {
                return current <= limit
            }
            
            override fun next(): Int {
                if (!hasNext()) {
                    throw NoSuchElementException()
                }
                val result = current
                current += 2
                return result
            }
        }
    }
}

// 3. 사용 예제
fun main() {
    println("=== NumberRange 사용 ===")
    val range = NumberRange(1, 5)
    
    // for 루프로 사용 가능
    for (num in range) {
        print("$num ")
    }
    println()
    
    // 고차 함수 사용 가능
    val doubled = range.map { it * 2 }
    println("Doubled: $doubled")
    
    val filtered = range.filter { it % 2 == 0 }
    println("Filtered (even): $filtered")
    
    println("\n=== EvenNumbers 사용 ===")
    val evens = EvenNumbers(10)
    
    for (num in evens) {
        print("$num ")
    }
    println()
    
    // forEach 사용
    println("\nUsing forEach:")
    evens.forEach { println("Even number: $it") }
    
    // sum, count 등 집계 함수 사용
    println("Sum of evens: ${evens.sum()}")
    println("Count: ${evens.count()}")
    
    println("\n=== 다중 순회 ===")
    // Iterable은 여러 번 순회 가능
    println("First iteration:")
    range.forEach { print("$it ") }
    println("\nSecond iteration:")
    range.forEach { print("$it ") }
}
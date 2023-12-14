/** 정수로 이루어진 배열 numbers
 *  자신보다 뒤에있는 숫자 중에서 자신보다 크면서 가장 가까이 있는수를 뒷 큰수
 *  단, 뒷 큰수가 존재하지 않은 원소 -1을 담는다.
 *
 *  4~1,000,000 numbers의 길이
 *
 *  [2 3 3 5] -> [3 3 5] -> [3 5] -> [5]
 *  2             5           5       -1
 *  [9,1,5,3,6,2] -> [1,5,3,6,2] -> [5,3,6,2] -> [3,6,2] -> [6,2] ->[2]
 *  -1               5              6            6          -1      -1
 *
 *  큐의 형태네..
 */

class Que{
    var que = Array<Int>(MAX_SIZE,{-1})
    var front = 0
    var rear = -1
    var size = 0

    fun isEmpty():Boolean = (size==0)

    fun enqueue(i :Int){
        rear = (rear+1)% MAX_SIZE
        que[rear] = i
        size++
    }
    fun dequeue():Int{
        val answer = que[front]
        front = (front+1)% MAX_SIZE
        size--
        return answer
    }

    //뒷 큰수 찾기!
    fun checkMax(i : Int):Int{
        for(idx in front..rear){
            if(i<que[idx]) return que[idx]
        }
        return -1
    }

    companion object{
        const val MAX_SIZE = 1000000
    }
}
class Solution {


    fun solution(numbers: IntArray): IntArray {
        var answer: IntArray = IntArray(numbers.size)
        var que = Que()

        //#1 que에 값 대입
        numbers.forEach{
            que.enqueue(it)
        }
        //#2 dequeue 한후, 그 Que안에 큰수 찾기
        var count = 0
        while(!que.isEmpty()){
            var value = que.dequeue()
            answer[count++] = que.checkMax(value)
        }
        return answer
    }
}

fun main(){
    var a = Solution()
    a.solution(intArrayOf(2,3,3,5))
    a.solution(intArrayOf(9,1,5,3,6,2))
}
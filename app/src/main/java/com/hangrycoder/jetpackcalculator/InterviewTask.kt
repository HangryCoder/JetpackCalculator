package com.hangrycoder.jetpackcalculator

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.withContext
import kotlin.system.measureTimeMillis

class InterviewTask {
}


/**
 * The string below contains data in a comma-separated values format (CSV).
 * The data represents a user database or spreadsheet,
 * where each line is one user, and each cell is separated by a comma.
 * If you imported it into a spreadsheet, it would look like this:
 *
 * | Name             | Age | Address             | Phone Number | Email                 | Favorite Number | Employed |
 * +------------------+-----+---------------------+--------------+-----------------------+-----------------+----------+
 * | Burgess Greasley | 33  | 04 Ridge Oak Street | 649-893-5297 | bgreasley@4shared.com | 337             | true     |
 *
 * (just for illustration, you don't need to format the data like this)
 *
 * Your task is to:
 *  - read in all the values in the string below
 *  - sort them by the person's age
 *  - write the sorted data back out as comma-separated-values (same format as input)
 */

fun reverseWords(string: String): String {

    val inputArray = string.toCharArray()
    reverse(inputArray, 0, string.length - 1)

    var start = 0
    for (end in inputArray.indices) {
        if (inputArray[end] == ' ') {
            reverse(inputArray, start, end - 1)

            start = end + 1
        }
    }

    reverse(inputArray, start, inputArray.size - 1)

    return String(inputArray)
}

fun reverse(str: CharArray, start: Int, end: Int) {
    var i = start
    var j = end
    while (i < j) {
        val temp = str[i]
        str[i] = str[j]
        str[j] = temp
        i++
        j--
    }
}

fun old_main(args: Array<String>) {
    val csv = """
Name,Age,Address,Phone Number,Email,Favorite Number,Employed
Burgess Greasley,33,04 Ridge Oak Street,649-893-5297,bgreasley1@4shared.com,337,true
Derwin Brunel,13,0843 Bunting Hill,790-611-6437,dbrunel2@discovery.com,961,true
Sheffie Spadotto,55,5298 Grover Court,265-791-1163,sspadotto3@salon.com,479,true
Courtney Fearnyhough,63,2102 Garrison Circle,502-971-1269,cfearnyhough4@wikipedia.org,876,true
Melloney Stickens,19,97934 Crownhardt Plaza,145-301-1842,mstickens5@facebook.com,852,false
Ellery Geere,53,7 Kedzie Center,515-764-9730,egeere6@adobe.com,516,false
Boone Malimoe,19,7259 Anhalt Court,776-410-0007,bmalimoe0@canalblog.com,142,false
Nikki Goodere,61,6 Canary Parkway,827-542-0107,ngoodere7@chicagotribune.com,0,true
Annabela Riddel,34,9132 Westridge Way,605-920-8468,ariddel8@w3.org,672,false
""".trimIndent()

    val values = csv.split("\n").drop(1).sortedBy {
        val row = it.split(",")
        row[1].toInt()
    }

    println(values)

    val hits = """
        /home /home/account /home/account/edit_address
        /home /home/transactions
        /home /home/offers
        /home /home/offers /home /home/account
        /home /home/transactions /home /home/account /home /home/transactions /home
        /home /home/offers /home/transactions /home
        /home /home/offers /home/transactions /home /home/offers /home /home/transactions
        /home /home/offers
        /home /home/transactions
        /home /home/transactions /home /home/offers
    """.trimIndent()

    // find the count of the each dir

    //Output
    /*
    * /home 18
    /home/account 3
    /home/account/edit_address 1
    /home/transactions 8
    /home/offers 7

    OR This one:::

    home 37
    account 4
    edit_address 1
    transactions 8
    offers 7
    * */

    val directoryMap = mutableMapOf<String, Int>()
    hits.split("\n").forEach {
        it.split(" ").forEach { directory ->
            directory.split("/").forEach { dir ->
                if (dir.isNotEmpty()) {
                    val value = directoryMap[dir] ?: 0
                    directoryMap[dir] = value + 1
                }
            }
        }
    }

    println("Directory Map $directoryMap")

    //How do you reverse the order of words in a given string
    val string = "We love kotlin programming"

    //Output - programming kotlin love We

    val output = reverseWords(string)//string.split(" ").reversed().joinToString(" ")

    println(output)


    val newCsv = """
                Name,Department,salary,
                Burgess Greasley,IT,65000
                Derwin Brunel,Finance, 50000
                Sheffie Spadotto,IT, 75000
                Courtney Fearnyhough,Finance, 70000
                Melloney Stickens,HR, 45000
                Ellery Geere,HR,65000
                """.trimIndent()
    /* 1. convert string to list
             2. remove header from list
             3. calculate average of salary of each department
     4. create map of average of salary with department
             Output {It= 70000, Finance= 60000, HR=55000  }*/

    val salaryMap = mutableMapOf<String, MutableList<Int>>()
    newCsv.split("\n").drop(1).forEach {
        val row = it.split(",")
        val department = row[1].trim()
        val salary = row[2].trim().toInt()
        println(department)
        println(salary)

        val list = salaryMap[department] ?: mutableListOf()
        list.add(salary)
        salaryMap[department] = list
    }


    val result = salaryMap.mapValues {
        it.value.average().toInt()
    }

    println(result)


}

/*class MainRepository(
    private val userService: UserService,
    private val weatherService: WeatherService
) {
    fun getCityWeather(id: Int) = runBlocking {
        val user = withContext(Dispatchers.IO) {
            userService.getUser(id)
        }

        val weather = async(Dispatchers.IO) {
            weatherService.getWeather(user.location)
        }

        val data = weather.await()

        println("Weather in ${user.location}: $data")
    }
}

interface UserService {
    suspend fun getUser(id: Int): User
}

interface WeatherService {
    suspend fun getWeather(location: String): String
}*/

data class User(val id: Int, val name: String, val location: String)

fun old_main() = runBlocking {

    val scope = CoroutineScope(Job() + Dispatchers.IO)
    val userDetails = scope.launch {
        delay(1000)
        throw Exception()
        println("User Details")
    }

    val weatherDetails = scope.async {
        delay(2000)
        println("Fetched Weather Details")
        "Panaji 20C"
    }

    val time = measureTimeMillis {
        userDetails.join()
        weatherDetails.await()
    }

    println(time)
}

suspend fun getUser(id: Int): User {
    delay(3000)
    throw Exception("User exception")
    return User(id, "Sonia", "Taleigao")
}

suspend fun getWeather(location: String): String {
    delay(1000)
    // throw Exception("Weather exception")
    return "Hot"
}

suspend fun getWeather(): String {
    delay(1000)
    //throw Exception("Weather exception")
    return "Hot"
}

suspend fun getCityWeather(id: Int) /*= runBlocking*/ {
    println("getCityWeather Starting")
    /*   supervisorScope {
           val user = async(Dispatchers.IO) {
               getUser(id)
           }

           val location = try {
               user.await().location
           } catch (e: Exception) {
               println(e.message)
               "Margao"
           }
           println("Fetched Location $location")

           val weather = async(Dispatchers.IO) {
               getWeather(location)
           }

           val data = weather.await()
           println("Fetched Weather $data")

           println("Weather in ${location}: $data")
       }*/

    /*val job = CoroutineScope(SupervisorJob() + Dispatchers.IO).launch {
        println(Thread.currentThread().name)
        val user = getUser(id)
        val location = user.location

        println("Fetched Location $location")

        val weather = getWeather(location)
        val data = weather
        println("Fetched Weather $data")

        println("Weather in ${location}: $data")
    }
    job.join()
    println("getCityWeather Ended")*/

    supervisorScope {
        try {
            val user = withContext(Dispatchers.IO) {
                getUser(id)
            }
            println("Fetched user $user")
        } catch (e: Exception) {
            println(e.message)
        }


        /*val location = try {
            user.await().location
        } catch (e: Exception) {
            println(e.message)
            "Margao"
        }
        println("Fetched Location $location")*/

        try {
            val weather = withContext(Dispatchers.IO) {
                getWeather("location")
            }
            println("Fetched weather $weather")
        } catch (e: Exception) {
            println(e.message)
        }


        /*val time = measureTimeMillis {
            val data = weather + user
            println("Fetched Weather $data")
        }

        println(time)*/


        /*  val data1 = user.await()

          println("User Location $data1")*/

        // println("User: $data1 Weather: $data")
        // println("Weather in ${location}: $data")
    }

    println("getCityWeather Ended")
}

fun main() {
    val transactions = """
        Burger Place - Restaurant
        01/15/24 - 12.99$
         
        Pastas! - Restaurant
        01/14/24 - 32.15$
         
        Landlord Inc. - Rent
        01/13/24 - 1500$
         
        The Movie Theater - Entertainment
        01/29/24 - 25.99$
         
        Foods-R-us - Groceries
        01/07/2 - 166.43$
         
        Foods-R-us - Groceries
        01/07/29 - 200.23$
        """.trimIndent()

    /*Desired Output:
    *Transactions
    Restaurant
    - Burger Place: 12.99$
    - Pastas!: 32.15$
    Rent
    - Landlord Inc.: 1500$
    Entertainment
    - The Movie Theater: 25.99$
    Groceries,
    - Foods-R-us: 166.43$
    - Foods-R-us: 200.23$*/

    val transMap = transactions.split("\n", " - ").filter { it != " " }.chunked(4)
        .groupBy({ it[1] }, { "${it[0]}: ${it[3]}" })

    println("Transactions")
    transMap.forEach { (key, value) ->
        println(key)
        value.forEach {
            println("- $it")
        }
    }
}
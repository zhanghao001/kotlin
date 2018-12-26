package base

import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

/**
 * @author hao_zhang3
 * @date 2018-12-26
 */
class HasTempFolder {
    //folder对应一个private属性,一个getter方法一个setter方法. Rule注解只能在public上.故指定了getter方法
    @get: Rule
    val folder = TemporaryFolder()

    @Test
    fun testUsingTempFolder() {
        val createFile = folder.newFile("myfile.txt")
        val createFolder = folder.newFolder("subfolder")
    }
}
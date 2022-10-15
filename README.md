# AndroidZip [![](https://jitpack.io/v/buggysofts-com/AndroidZip.svg)](https://jitpack.io/#buggysofts-com/AndroidZip)
A zip explorer library for android that uses <b>DocumentFile</b> object as its source. It uses my **StreamZip** library as its base. Its functionalities are similar to the standard java **ZipFile** class.

<br />

## Import
Add JitPack repository to your project level build.gradle file
```
...

allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
Or, in newer android projects, if you need to add the repository in settings.gradle file...
```
...

dependencyResolutionManagement {
    ...
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
Finally, add these two dependencies to your app/module level build.gradle file
```
...

dependencies {
    ...
    implementation 'com.github.buggysofts-com:StreamZip:v1.0.1'
    implementation 'com.github.buggysofts-com:AndroidZip:v1.0.1'
}
```
And you are done importing the library.

<br />

## Sample codes

To create an instance  do something like...

```
AndroidZip zip = null;
try {
    zip = new AndroidZip(MainActivity.this, documentFile);
} catch (Exception e) {
    e.printStackTrace();
} finally {
    if(zip != null){
        List<ZipEntry> entries = zip.entries();
        for (int i = 0; i < entries.size(); i++) {
            // do something
        }
    }
}
```

<br />

Then you can use different methods that are similar to the standard java ZipFile class. For example here are the
publicly available methods.

- ```getEntry(String name)``` Returns the entry mapped with the specified name, or null if there is no entry mapped with that name.
- ```entries()``` Returns all the available entries as a list of <b>ZipEntry</b>.
- ```getInputStream(...)``` Opens(and returns) a bounded input stream currently positioning at the start of the requested entry's data block.
- ```size()``` Returns the total number of available entries.
- ```getComment()``` Returns the principal comment of the zip file.
- ```close()``` Closes the zip file, and any subsequent call to <b>getInputStream(...)</b> will throw an exception. However, other methods of the class that are saved in memory will still be available after call to <b>close()</b>.

**Please Note**

- The **ZipEntry** we mentioned above is a part of this library and has similar methods as the standard **ZipEntry**
  class
  in java jdk.
- If you do not have a **ZipEntry** instance, and only have the name of the entry, you can use the minimal
  constructor (
  i.e.  ```ZipEntry(String name)```) to obtain an input stream. Of course, you would get an exception if the entry does
  not
  exist.

<br />

### Performance

The performance is similar to the Standard **ZipFile** class. Before this, the only way to read a zip file in this kind
of situation was to use the **ZipInputStream** class which basically reads every byte in its way to get to the next
entry. That is, to list, or to get data of all the entries of a zip file, it is equivalent of reading the whole file.
Imagine you have to read some metadata within some big zip files, may be 100 zip files, think how much time it would
take!
Of course, you can use some caching technique, which I was doing for a long time, in fact there is still a library in
the git repo, which does exactly that. But in any way, that is not enough, it takes a lot of memory, and the performance
is limited to many constraints.

<br />

Please share & rate the library if you find it useful.

### Happy coding!

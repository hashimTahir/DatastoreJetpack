# Prefrence DataStore replacement for shared prefrences

DataStore is a new and improved data storage solution, part of
 jetpack aimed at replacing SharedPreferences. Built on Kotlin coroutines
  and Flow, DataStore provides two different implementations:

#### 1->Proto DataStore
         that stores typed objects (backed by protocol buffers)

##### 2-> Preferences DataStore, that stores key-value pairs.
        Data is stored asynchronously, consistently, and transactionally,
        overcoming some of the drawbacks of SharedPreferences.


For most of the applications prefences datastore is enough. As it is simple and
easy to impplement. Whears proto datastore, in my opnion is a bit of a overkill
and useless. As most of the apps nowadays have room database and custom objects
can be stored there instead of proto datastore.

Proto DataStore requires a predefined schema in a proto file in the
app/src/main/proto/ directory. This schema defines the type for the objects
that are to be persisted in Proto DataStore.


There are two steps involved in creating a Proto DataStore to store typed objects:

1->  Define a class that implements Serializer<T>, where T is the type defined in
the proto file. This serializer class tells DataStore how to read and write your
data type. Make sure  a default value for the serializer is defined to be used
if there is no file created yet.

2->Use the property delegate created by dataStore to create an instance of
DataStore<T>, where T is the type defined in the proto file. This call should be
made once at the top level of the kotlin file and access it through this property
 delegate throughout the rest of your app. The filename parameter tells DataStore
 which file to use to store the data, and the serializer parameter tells DataStore
 the name of the serializer class defined in step 1.

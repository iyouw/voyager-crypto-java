add_library(kcrypto SHARED converter.c crypto_base.c native.c converter.h crypto_base.h)

# include directories
set(OPENSSL_WIN "C:/Program Files/OpenSSL")
set(OPENSSL_LIB_WIN "${OPENSSL_WIN}/lib/libcrypto.lib")

set(INCLUDE_PATHS ${CMAKE_CURRENT_SOURCE_DIR})

# openssl
if(MSVC)
  list(APPEND INCLUDE_PATHS "${OPENSSL_WIN}/include")
endif()

# jni
set(JAVA_INCLUDE "$ENV{JAVA_HOME}/include")

list(APPEND INCLUDE_PATHS ${JAVA_INCLUDE})

if(CMAKE_SYSTEM_NAME STREQUAL "Windows")
  list(APPEND INCLUDE_PATHS "${JAVA_INCLUDE}/win32")
endif()

if(CMAKE_SYSTEM_NAME STREQUAL "Linux")
  list(APPEND INCLUDE_PATHS "${JAVA_INCLUDE}/linux")
endif()

target_include_directories(kcrypto PUBLIC ${INCLUDE_PATHS})

# link librarys
if(MSVC)
  list(APPEND LINK_LIBS ${OPENSSL_LIB_WIN})
else()
  list(APPEND LINK_LIBS "crypto" "m")
endif()

target_link_libraries(kcrypto ${LINK_LIBS})
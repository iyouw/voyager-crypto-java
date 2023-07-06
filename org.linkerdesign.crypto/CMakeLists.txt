cmake_minimum_required(VERSION 3.20)
project(linkerdesign.crypto)

add_subdirectory(lib/src/main/c)

set(RESOURCE_NATIVE_DIR "${PROJECT_SOURCE_DIR}/lib/src/main/resources/native")

if (CMAKE_SYSTEM_NAME STREQUAL "Windows")
  install(TARGETS kcrypto DESTINATION "${RESOURCE_NATIVE_DIR}/win")
elseif(CMAKE_SYSTEM_NAME STREQUAL "Linux")
  install(TARGETS kcrypto DESTINATION "${RESOURCE_NATIVE_DIR}/linux")
else()
  message(STATUS "not support yet!")
endif()
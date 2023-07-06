#include <stdlib.h>
#include <string.h>

#include "stream.h"

Stream *stream_create(long capacity)
{
  Stream *stream = malloc(sizeof(Stream));
  stream->capacity = capacity;
  stream->length = 0;
  stream->data = malloc(capacity);
  return stream;
}

unsigned char *stream_get_write_ptr(Stream *stream)
{
  return stream->data + stream->length;
}

long stream_get_available(Stream *stream)
{
  return stream->capacity - stream->length;
}

int stream_ensure_capacity(Stream *stream, long length)
{
  long available = stream_get_available(stream);
  if (available < length + 1)
  {
    long capacity = stream->capacity * 2;
    if (capacity < length)
    {
      capacity = length + stream->capacity + 1;
    }
    stream->capacity = capacity;
    stream->data = realloc(stream->data, capacity);
  }
  return 0;
}

int stream_write(Stream *stream, long length)
{
  stream->length += length;
  return 0;
}

void stream_free(Stream *stream)
{
  if (NULL == stream) return;
  free(stream->data);
  free(stream);
}
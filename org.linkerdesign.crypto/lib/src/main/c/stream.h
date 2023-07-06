#ifndef LINKER_DESIGN_STREAM_H

#define LINKER_DESIGN_STREAM_H

typedef struct Stream
{
  long capacity;
  long length;
  unsigned char *data;
} Stream;

Stream *stream_create(long capacity);

unsigned char *stream_get_write_ptr(Stream *stream);

long stream_get_available(Stream *stream);

int stream_write(Stream *stream, long length);

int stream_ensure_capacity(Stream *stream, long length);

void stream_free(Stream *stream);

#endif
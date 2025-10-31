// whisper.h
#ifndef WHISPER_H
#define WHISPER_H

#include <vector>
#include <string>

struct whisper_context;

struct whisper_params {
    int32_t n_threads = 4;
    int32_t n_max_text_ctx = 16384;
    int32_t offset_ms = 0;
    int32_t duration_ms = 0;

    bool translate = false;
    bool no_context = true;
    bool print_special = false;
    bool print_progress = false;
    bool print_realtime = false;
    bool print_timestamps = true;

    std::string language = "en";
    std::string model = "models/ggml-tiny.en.bin";
};

whisper_context * whisper_init_from_file(const char * path_model);
void whisper_free(whisper_context * ctx);

int whisper_full(whisper_context * ctx, const whisper_params & params, const float * samples, int n_samples);
int whisper_full_n_segments(whisper_context * ctx);
std::string whisper_full_get_segment_text(whisper_context * ctx, int i_segment);
int64_t whisper_full_get_segment_t0(whisper_context * ctx, int i_segment);
int64_t whisper_full_get_segment_t1(whisper_context * ctx, int i_segment);

#endif // WHISPER_H

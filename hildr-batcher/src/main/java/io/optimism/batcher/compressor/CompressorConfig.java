/*
 * Copyright 2023 q315xia@163.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package io.optimism.batcher.compressor;

import io.optimism.batcher.config.Config;

/**
 * Compressor Config.
 *
 * @param targetFrameSize To target when creating channel frames. Note that if the realized
 *     compression ratio is worse than the approximate, more frames may actually be created. This
 *     also depends on how close the target is to the max frame size.
 * @param targetNumFrames To create in this channel. If the realized compression ratio is worse than
 *     approxComprRatio, additional leftover frame(s) might get created.
 * @param approxComprRatio ApproxComprRatio to assume. Should be slightly smaller than average from
 *     experiments to avoid the chances of creating a small additional leftover frame.
 * @param kind Kind of compressor to use. Must be one of KindKeys. If unset, NewCompressor will
 *     default to RatioKind.
 * @author thinkAfCod
 * @since 0.1.1
 */
public record CompressorConfig(int targetFrameSize, int targetNumFrames, String approxComprRatio, String kind) {

    /**
     * Create CompressorConfig instance from Config instance.
     *
     * @param config Config instance
     * @return CompressorConfig instance
     */
    public static CompressorConfig from(Config config) {
        return new CompressorConfig(
                config.targetFrameSize(), config.targetNumFrames(), config.approxComprRatio(), Compressors.RatioKind);
    }
}

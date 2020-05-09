package com.alimu.probe.util;

import java.io.File;

import ws.schild.jave.AudioAttributes;
import ws.schild.jave.Encoder;
import ws.schild.jave.EncoderException;
import ws.schild.jave.EncodingAttributes;
import ws.schild.jave.MultimediaObject;
import ws.schild.jave.VideoAttributes;

public class ConvertVideoUtil {

	public static void convertVideoToMP4(String source, String targetPath) {
		File file = new File(source);
        MultimediaObject multimediaObject = new MultimediaObject(file);
        try {
            VideoAttributes video = new VideoAttributes();
            //设置视频编码
            video.setCodec("h264");
            File target = new File(targetPath);
            AudioAttributes audio = new AudioAttributes();
            //设置编码器名称
            audio.setCodec("aac");
            EncodingAttributes attrs = new EncodingAttributes();
            //设置转换后的格式
            attrs.setFormat("mp4");
            attrs.setAudioAttributes(audio);
            attrs.setVideoAttributes(video);
            Encoder encoder = new Encoder();
            encoder.encode(multimediaObject, target, attrs);
            file.delete();
        } catch (EncoderException e) {
            e.printStackTrace();
        }
    }
}

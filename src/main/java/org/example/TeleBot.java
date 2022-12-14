package org.example;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendDocument;
import com.pengrad.telegrambot.request.SendMessage;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class TeleBot {
    private final String TOKEN_BOT = "5964950127:AAEU8HWG5iC6PpYAiOxKuGomH0EXNGzJXgU";
    private final String CHAT_ID = "5158825675";
    private final OkHttpClient client;
    private final TelegramBot bot;

    public TeleBot() {
//        client = new OkHttpClient.Builder()
//                .connectTimeout(1, TimeUnit.MINUTES)
//                .writeTimeout(1, TimeUnit.MINUTES)
//                .readTimeout(1, TimeUnit.MINUTES)
//                .addInterceptor(chain -> {
//                    Request request = chain.request();
//                    Response response = null;
//                    IOException error = new IOException();
//                    try {
//                        response = chain.proceed(request);
//                    } catch (IOException e) {
//                        error = e;
//                    }
//                    int tryCount = 0;
//                    while ((response == null || !response.isSuccessful()) && tryCount < 6) {
//                        try {
//                            Thread.sleep(300);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                        tryCount++;
//                        System.out.println("Retry " + tryCount);
//                        try {
//                            response = chain.proceed(request);
//                        } catch (IOException e) {
//                            error = e;
//                        }
//                    }
//                    if (response != null) return response;
//                    else return
//                })
//                .build();
        client = new OkHttpClient()
                .newBuilder()
                .build();

        bot = new TelegramBot.Builder(TOKEN_BOT)
                .okHttpClient(client)
                .build();

    }

    public boolean sendMessage(String msg) {
        return bot.execute(new SendMessage(CHAT_ID, msg)).isOk();
    }

    public boolean sendDocument(String caption, File file) {
        return bot.execute(new SendDocument(CHAT_ID, file).caption(caption)).isOk();
    }

    public boolean sendDocument(File file) {
        return bot.execute(new SendDocument(CHAT_ID, file)).isOk();
    }

    public void close() {
        if (client!=null) {
            client.dispatcher().executorService().shutdown();
            client.connectionPool().evictAll();
        }
    }

    public static void main(String[] args) {
        TeleBot bot = new TeleBot();
        try {
//            bot.sendMessage("hello");
            File file = new File("/home/phukaioh/pii-evaluate-result.xlsx");
            bot.sendDocument("pii-evaluete",file);
        } finally {
            bot.close();
        }
    }


//    public static void main(String[] args) {
//        String urlString = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s";
//
//        //Add Telegram token (given Token is fake)
//        String apiToken = "5964950127:AAEU8HWG5iC6PpYAiOxKuGomH0EXNGzJXgU";
//
//        //Add chatId (given chatId is fake)
//        String chatId = "5158825675";
//        String text = "Hello world!";
//
//        urlString = String.format(urlString, apiToken, chatId, text);
//
//        try {
//            URL url = new URL(urlString);
//            URLConnection conn = url.openConnection();
////            InputStream is = new BufferedInputStream(conn.getInputStream());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}

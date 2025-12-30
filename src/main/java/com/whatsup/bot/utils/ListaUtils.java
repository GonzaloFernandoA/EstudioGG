package com.whatsup.bot.utils;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListaUtils
{
    // Devuelve un string con los elementos numerados, usando emojis de fondo azul para los números del 1 al 10.
    public static String formarListaNumerada(List<String> items) {
        if (items == null || items.isEmpty())
            return "";

        String[] blueNumberEmojis = {
                "1️⃣", "2️⃣", "3️⃣", "4️⃣", "5️⃣", "6️⃣", "7️⃣", "8️⃣", "9️⃣", "🔟"
        };

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < items.size(); i++) {
            String numeroEmoji;
            if (i < 10)
                numeroEmoji = blueNumberEmojis[i];
            else
                numeroEmoji = String.valueOf(i + 1);

            sb.append(numeroEmoji).append(" ").append(items.get(i)).append("\n");
        }
        return sb.toString();
    }

}

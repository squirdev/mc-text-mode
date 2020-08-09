package net.squirdev.events;

import com.github.steveice10.mc.protocol.packet.ingame.client.ClientChatPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.ServerChatPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.ServerJoinGamePacket;
import com.github.steveice10.packetlib.event.session.DisconnectedEvent;
import com.github.steveice10.packetlib.event.session.PacketReceivedEvent;
import com.github.steveice10.packetlib.event.session.SessionAdapter;
import com.google.gson.Gson;

import java.util.Map;

public class ServerEvents extends SessionAdapter {
    @Override
    public void packetReceived(PacketReceivedEvent event) {
        if (event.getPacket() instanceof ServerChatPacket) {
            ServerChatPacket packet = event.<ServerChatPacket>getPacket();
            Map ez = new Gson().fromJson(packet.getMessage().toJsonString(), Map.class);
            System.out.println(ez.get("text"));
        }

        if (event.getPacket() instanceof ServerJoinGamePacket) {
            ServerJoinGamePacket packet = event.<ServerJoinGamePacket>getPacket();
            System.out.println("Connected.");
        }

        if (event.getPacket() instanceof ClientChatPacket) {
            ClientChatPacket packet = event.getPacket();
            System.out.println("You said " + packet.getMessage());
        }
    }

    @Override
    public void disconnected(DisconnectedEvent event) {
        System.out.println("Disconnected from server: " + event.getReason());
    }
}

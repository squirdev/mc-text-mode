package net.squirdev.threads;

import com.github.steveice10.mc.auth.exception.request.RequestException;
import com.github.steveice10.mc.protocol.MinecraftConstants;
import com.github.steveice10.mc.protocol.MinecraftProtocol;
import com.github.steveice10.mc.protocol.packet.ingame.client.ClientChatPacket;
import com.github.steveice10.packetlib.tcp.TcpSessionFactory;
import net.squirdev.events.ServerEvents;

public class Client extends Thread {
    private final String name;
    private final String password;
    private final String host;
    public static final String suffix = " | Sent using SquirChat";
    private final int port;

    public static com.github.steveice10.packetlib.Client client = null;

    public Client(String name, String password, String host, int port) {
        this.name = name;
        this.password = password;
        this.host = host;
        this.port = port;
    }

    public void run() {
        join(name,password,host,port);
    }

    private void join(String name, String password, String host, int port) {
        MinecraftProtocol protocol = null;
        try {
            protocol = new MinecraftProtocol(name, password);
        } catch(RequestException e) {
            protocol = new MinecraftProtocol(name);
        }

        com.github.steveice10.packetlib.Client client = new com.github.steveice10.packetlib.Client(host, port, protocol, new TcpSessionFactory(null));

        client.getSession().setFlag(MinecraftConstants.AUTH_PROXY_KEY, null);
        client.getSession().addListener(new ServerEvents());

        client.getSession().connect();

        Client.client = client;
    }

    public static void say(String text, boolean suffix) {
        String t;
        if (suffix) {
            t = text + Client.suffix;
        } else {
            t = text;
        }
        client.getSession().send(new ClientChatPacket(t));
    }
}

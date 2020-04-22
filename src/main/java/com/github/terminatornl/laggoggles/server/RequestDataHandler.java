package com.github.terminatornl.laggoggles.server;

import com.github.terminatornl.laggoggles.packet.CPacketRequestServerData;
import com.github.terminatornl.laggoggles.packet.SPacketServerData;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.ArrayList;
import java.util.UUID;

public class RequestDataHandler implements IMessageHandler<CPacketRequestServerData,SPacketServerData>{

    public static final ArrayList<UUID> playersWithLagGoggles = new ArrayList<>();

    @SubscribeEvent
    public void onLogout(PlayerEvent.PlayerLoggedOutEvent e){
        playersWithLagGoggles.remove(e.player.getGameProfile().getId());
    }

    @Override
    public SPacketServerData onMessage(CPacketRequestServerData cPacketRequestServerData, MessageContext ctx){
        if(playersWithLagGoggles.contains(ctx.getServerHandler().player.getGameProfile().getId()) == false) {
            playersWithLagGoggles.add(ctx.getServerHandler().player.getGameProfile().getId());
        }
        return new SPacketServerData(ctx.getServerHandler().player);
    }
}

package coderscampus.assignment14.channelRepository;

import coderscampus.assignment14.domain.Channel;

public class ChannelRepository {
    private Map<String, Channel> channels = new HashMap<>();

    public void addChannel(Channel channel) {
        channels.put(channel.getId(), channel);
    }

    public Optional<Channel> getChannelById(String id) {
        return Optional.ofNullable(channels.get(id));
    }

    public Iterable<Channel> getAllChannels() {
        return channels.values();
    }
}

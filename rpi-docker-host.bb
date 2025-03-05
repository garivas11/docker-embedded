inherit core-image

SPLASH = "psplash-raspberrypi"

IMAGE_INSTALL += " \
    tzdata \
    docker \ 
    kernel-modules \
	iptables \               
"
IMAGE_FEATURES += "ssh-server-dropbear splash"

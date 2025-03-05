# Docker for embedded targets

This project contains two basic recipes to implement a Docker host to run containers on embedded targets.
- container-test-image.bb : Recipe to create test container.
- rpi-docker-host.bb : Recipe to create host container for a Raspberry Pi target.

## Procedure to create images

- Get meta-virtualization layer and checkout to specific branch:
    ```
    git clone git://git.yoctoproject.org/meta-virtualization
    ```
- Include meta-filesystems and meta-virtualization layers on bblayers.conf file:
    ```
      ${TOPDIR}/../meta-openembedded/meta-filesystems \
      ${TOPDIR}/../meta-virtualization \
    ```
- Add to local.conf or distro configuration:
    ```
    DISTRO_FEATURES:append = " virtualization" 
    ```
- Build host image: bitbake rpi-docker-host
- Build container image : bitbake container-test-image
- Deploy host container on target with usual method like SD card, Flash EMMC, etc.
```
docker run -it --net=bridge core-test-image:latest bash
```
## Deploy container on target:
-Extract container image to container-test-image.tar file : 
```
bzip2 -df container-test-image.tar.bz2
```
-Transfer container-test-image.tar file to target.
-Import docker image into target 
```
docker import container-test-image.tar core-test-image:latest
```

#Test run

Run test container from embedded target :
```
docker run -it --net=bridge core-test-image:latest bash
```
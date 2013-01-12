/* $XORP$ */

interfaces {
    interface eth0 {
  	vif eth0 {
			address 192.168.1.100 {
				prefix-length: 24
			}
		}
    }
}

protocols {
    static {
        disable: false
		route 0.0.0.0/0 {
			next-hop: 192.168.1.1
		}
	}
    ospf4 {
		router-id: 192.168.1.100
		area 0.0.0.0 {
			interface eth0 {
				vif eth0 {
					address 192.168.1.100 {
					}
				}
			}
		}
    }
}

fea {
    unicast-forwarding4 {
		disable: false
    }
    click {
		disable: false
		duplicate-routes-to-kernel: true

		kernel-click {
			disable: false
			install-on-startup:	true
			kernel-click-modules: "/usr/local/lib/proclikefs.ko:/usr/local/lib/click.ko"
			mount-directory: "/click"
			kernel-click-config-generator-file: "/etc/xorp/click_generator"
		}

		user-click {
			disable: true
			command-file: "/usr/local/bin/click"
			command-extra-arguments: "-R"
			command-execute-on-startup: true
			control-address: 127.0.0.1
			control-socket-port: 13000
			startup-config-file: "/dev/null"
			user-click-config-generator-file: "/etc/xorp/click_generator"
		}
    }
}


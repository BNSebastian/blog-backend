#!/bin/bash

# Run `caddy fmt` to format the Caddyfile
sudo caddy fmt --overwrite

# Run `caddy adapt` to adapt the Caddyfile to its JSON config
sudo caddy adapt

# Reload Caddy to apply the changes
sudo caddy stop

sudo caddy start


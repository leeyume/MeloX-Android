#!/usr/bin/env python3
"""Import optional assets from a locally obtained miku-navigation plugin.
Use only when you have appropriate rights. This script grants no artwork rights.
"""
import argparse
import shutil
from pathlib import Path

MAPPING = {
    'icons/01_home.png': 'icons/home.png',
    'icons/streaming/02_discover.png': 'icons/explore.png',
    'icons/02_all_songs.png': 'icons/library.png',
    'icons/11_radio_podcast.png': 'icons/podcasts.png',
    # Closest existing source icon for Android's downloads route.
    'icons/13_import_songs.png': 'icons/downloads.png',
    'icons/streaming/04_cloud_drive.png': 'icons/cloud.png',
    'icons/settings/01_general.png': 'icons/settings.png',
    'artwork/miku-home.jpg': 'artwork/miku-home.jpg',
    'artwork/miku-sky.webp': 'artwork/miku-sky.webp',
    'artwork/miku-bouquet.webp': 'artwork/miku-bouquet.webp',
}
# Preserve source names for category and state mappings.
for name in ('01_general', '02_playback', '03_dsp', '04_cache', '05_performance',
             '06_appearance', '07_desktop_lyrics', '08_shortcuts', '09_about'):
    MAPPING[f'icons/settings/{name}.png'] = f'icons/settings/{name}.png'
for name in ('peek', 'listening', 'empty', 'thinking', 'done'):
    MAPPING[f'artwork/states/miku-{name}.webp'] = f'artwork/states/miku-{name}.webp'

def main():
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument('plugin_dir', type=Path)
    parser.add_argument('--confirm-rights', action='store_true', help='Confirm appropriate permission for your intended use')
    parser.add_argument('--remove', action='store_true', help='Remove previously imported artwork before distributing a code-only build')
    args = parser.parse_args()
    target = Path(__file__).resolve().parents[1] / 'android/app/src/main/assets/miku'
    if args.remove:
        if target.exists():
            shutil.rmtree(target)
        print('Optional artwork removed; palette and stock icons remain available.')
        return
    if not args.confirm_rights:
        parser.error('Artwork has no clear redistribution grant. Check permission first, then pass --confirm-rights.')
    missing = [name for name in MAPPING if not (args.plugin_dir / name).is_file()]
    if missing:
        parser.error('Missing source files: ' + ', '.join(missing))
    for source, dest in MAPPING.items():
        out = target / dest
        out.parent.mkdir(parents=True, exist_ok=True)
        shutil.copy2(args.plugin_dir / source, out)
    for name in ('LICENSE', 'THIRD_PARTY_NOTICES.md', 'plugin.json'):
        if (args.plugin_dir / name).is_file():
            shutil.copy2(args.plugin_dir / name, target / name)
    print(f'Imported {len(MAPPING)} optional assets to {target}. Do not redistribute without applicable permission.')

if __name__ == '__main__':
    main()

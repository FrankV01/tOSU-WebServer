/**
 * tOSU-WebServer | An educational HTTP Server
 * Copyright (C) 2010  Frank Villasenor <Frank@TheOpenSourceU.com>
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
 
import java.util.ArrayList;
import java.util.List;


public class ArgumentFactory {
	public static Argument<String> newArgument( String Key, String Default, String HelpMessage ) {
		return new ArgumentImp<String>( Key, Default, HelpMessage );
	}
	
	public static List<Argument<String>> newArgumentList() {
		return new ArrayList< Argument<String> >();
	}
	
	public static ArgumentProcessor newArgumentProcessor( String[] args, 
			List<Argument<String>> PrefilledDefaults ) {
		return new BasicArgumentProcessor(args, PrefilledDefaults);
	}
}